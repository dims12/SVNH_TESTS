package com.inthemoon.deeplearning.svhndb.spring;

import com.inthemoon.deeplearning.svhndb.spring.service.BboxService;
import com.inthemoon.deeplearning.svhndb.spring.service.ImageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Dims on 10.03.2017.
 */
public class SVHNDBApp {

   public static void main(String[] args) {
      ApplicationContext context = new AnnotationConfigApplicationContext(SVHNDBConfig.class);
//      ImageSetService imageSetService = context.getBean(ImageSetService.class);
//      imageSetService.populateIfEmpty();

//      FilesystemService filesystemService = context.getBean(FilesystemService.class);
//      for(File file : filesystemService.getImageSetImageFiles(ImageSetService.DefaultImageSets[0])) {
//         System.out.println(file.toString());
//      }

      ImageService imageService = context.getBean(ImageService.class);
      imageService.populateImagesIfEmpty();

      BboxService bboxService = context.getBean(BboxService.class);
      bboxService.populateBboxIfEmpty();
   }
}
