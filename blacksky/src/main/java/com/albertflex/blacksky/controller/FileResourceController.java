package com.albertflex.blacksky.controller;

import com.albertflex.blacksky.domain.FileResource;
import com.albertflex.blacksky.query.PageQuery;
import com.albertflex.blacksky.service.FileResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/file_resource")
public class FileResourceController {

    private final FileResourceServices fileResourceServices;

    @Value("${app.file-resource.prefix}")
    private String prefix;

    @Autowired
    public FileResourceController(FileResourceServices fileResourceServices) {
        this.fileResourceServices = fileResourceServices;
    }

    public List<FileResource> list(PageQuery page) {
        page.start();
        return fileResourceServices.views();
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("name") String name) throws IOException {
        File dir = new File("." + this.prefix);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String name0 = file.getOriginalFilename();
        String format = name0.split("\\.")[1];

        FileResource resource = new FileResource();
        resource.setName(name);
        resource.setFormat(format);
        if (!fileResourceServices.add(resource)) {
            return "DATABASE ERROR";
        }
        file.transferTo(new File(dir.getAbsoluteFile() + File.separator + resource.getId() + "." + format));
        return "OK " + resource.getId();
    }

    @GetMapping("/get2/{id}")
    public ResponseEntity<StreamingResponseBody> download(@PathVariable("id") Long id) {
        FileResource resource = fileResourceServices.get(id);
        if (resource == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + resource.getName() + "." + resource.getFormat())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(outputStream -> {
                    String path = "." + this.prefix + File.separator + id + "." + resource.getFormat();
                    try (InputStream in = new FileInputStream(path)) {
                        StreamUtils.copy(in, outputStream);
                    } catch (IOException e) {

                    }
                });
    }

    @GetMapping("/{id}")
    public void download(
            @PathVariable("id") Long id,
            HttpServletRequest request, HttpServletResponse response) {

        FileResource resource = fileResourceServices.get(id);

        BufferedInputStream fis = null;
        OutputStream os = null;
        try {
            String path = "." + this.prefix + File.separator + id + "." + resource.getFormat();
            String fileName = resource.getName();
            response.setCharacterEncoding("UTF-8");
            fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            os = new BufferedOutputStream(response.getOutputStream());
            String f = resource.getFormat();
            boolean isPicture = f.equals("png") || f.equals("jpeg") || f.equals("jpg") || f.equals("gif") || f.equals("bmp");
            if (isPicture) {
                response.setContentType("image/png");
            } else {
                response.setContentType("application/octet-stream");
            }
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "." + resource.getFormat());
            ;
            os.write(buffer);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                }
                if (null != os) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/stream/{id}")
    public void stream() {
        //TODO
    }
}
