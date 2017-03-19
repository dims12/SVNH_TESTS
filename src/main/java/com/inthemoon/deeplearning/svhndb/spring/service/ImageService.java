package com.inthemoon.deeplearning.svhndb.spring.service;

import com.inthemoon.deeplearning.svhndb.model.Image;
import com.inthemoon.deeplearning.svhndb.model.ImageSet;
import com.inthemoon.deeplearning.svhndb.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Dims on 10.03.2017.
 */
@Component
public class ImageService {

   private final FilesystemService filesystemService;
   private final ImageSetService imageSetService;
   private final ImageRepo imageRepo;

   @Autowired
   public ImageService(FilesystemService filesystemService, ImageSetService imageSetService, ImageRepo imageRepo) {
      this.filesystemService = filesystemService;
      this.imageSetService = imageSetService;
      this.imageRepo = imageRepo;
   }


   public Iterable<Image> getImageSetImagesFromDirectory(ImageSet imageSet) {
      Stream<Image> stream = StreamSupport
         .stream( filesystemService.getImageSetImageFiles(imageSet).spliterator(), false)
         .map(new Function<File, Image>() {
            @Override
            public Image apply(File imageFile) {
               Image ans = new Image();
               String path = filesystemService.getPathRelativeToCorpusRoot(imageFile);
               int[] widhtheight = filesystemService.getImageWidthAndHeight(imageFile);
               ans.setImageSet(imageSet);
               ans.setPath(path);
               ans.setWidth( widhtheight[0] );
               ans.setHeight( widhtheight[1] );
               return ans;
            }
         });

      Iterable<Image> ans = stream::iterator;

      return ans;
   }




   public void populateImages() {

      imageRepo.deleteAll();

      for(ImageSet imageSet : imageSetService.getImageSetRepo().findAll()) {
         for(Image image : getImageSetImagesFromDirectory(imageSet) ) {
            imageRepo.save(image);
            System.out.println(image.toString());
         }
      }

   }

   public void populateImagesIfEmpty() {
      if( imageRepo.count() == 0 ) {
         populateImages();
      }
   }
}
