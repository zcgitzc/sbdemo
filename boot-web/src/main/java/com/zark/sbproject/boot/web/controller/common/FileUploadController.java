package com.zark.sbproject.boot.web.controller.common;

import com.google.common.io.Files;
import com.zark.sbproject.boot.web.controller.vo.ChunkUploadVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zark
 * @description
 * @date 2020-02-08 12:55
 */
@RestController
@RequestMapping("upload")
@Slf4j
public class FileUploadController {

    //TODO create by zark 2020-02-08 添加参数校验：文件size校验，分片个数校验
    @RequestMapping("/chunkUpload")
    public String chunkUpload(@RequestParam("file") MultipartFile file, ChunkUploadVO chunkUploadVO) throws Exception {
        log.info("temp dir:" + FileUtils.getTempDirectoryPath());
        String tempFilePath = FileUtils.getTempDirectoryPath() + File.separator + chunkUploadVO.getGuid()
                + File.separator + "temp_" + chunkUploadVO.getChunk();

        FileUtils.writeByteArrayToFile(new File(tempFilePath), file.getBytes());

        log.info("chunk file size:" + file.getSize());
        log.info("chunk infos:" + chunkUploadVO);
        return "uploadSuccess!";
    }

    @RequestMapping("/chunkMerge")
    public String chunkMerge(String fileName, String guid) throws Exception {
        String tempFileDirPath = FileUtils.getTempDirectoryPath() + File.separator + guid + File.separator;

        List<File> files = new ArrayList<>(FileUtils.listFiles(new File(tempFileDirPath), null, false));
        File finalFile = new File("/Users/zark/tempfile/" + fileName);
        if (finalFile.exists()) {
            finalFile.delete();
        }
        finalFile.createNewFile();

        Collections.sort(files, Comparator.comparing(File::getName));

        files.forEach(file -> {
            try {
                log.info("will merge file. file name :" + file.getName());
                FileUtils.writeByteArrayToFile(finalFile, FileUtils.readFileToByteArray(file), true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        //删除临时文件
        FileUtils.deleteDirectory(new File(tempFileDirPath));

        return "mergeSuccess!";
    }
}
