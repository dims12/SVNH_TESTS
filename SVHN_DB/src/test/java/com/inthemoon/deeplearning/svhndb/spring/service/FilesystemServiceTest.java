package com.inthemoon.deeplearning.svhndb.spring.service;

import com.inthemoon.deeplearning.svhndb.spring.SVHNDBConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

/**
 * Created by Dims on 10.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SVHNDBConfig.class})
public class FilesystemServiceTest {

   @Autowired
   FilesystemService filesystemService;

   @Test
   public void testDirectoryStreamAlone() {

      File file = filesystemService.getImageSetRoot(ImageSetService.DefaultImageSets[0]);

      try {
         Files
            .list(file.toPath())
            .forEach(new Consumer<Path>() {
               @Override
               public void accept(Path path) {
                  System.out.println(path.toString());
               }
            });
      } catch (IOException e) {
         throw new RuntimeException(e);
      }

   }

   @Test
   public void testGetImageSetImageFiles() {
      for(File file : filesystemService.getImageSetImageFiles(ImageSetService.DefaultImageSets[0])) {
         System.out.println(file.toString());
      }
   }

}