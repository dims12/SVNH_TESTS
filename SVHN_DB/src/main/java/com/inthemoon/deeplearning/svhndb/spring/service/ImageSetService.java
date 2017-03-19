package com.inthemoon.deeplearning.svhndb.spring.service;

import com.inthemoon.deeplearning.svhndb.model.ImageSet;
import com.inthemoon.deeplearning.svhndb.repo.ImageSetRepo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Dims on 10.03.2017.
 */
@Component
public class ImageSetService implements InitializingBean {

   public final static ImageSet[] DefaultImageSets = new ImageSet[] {
      new ImageSet(1, "Train", "train/"),
      new ImageSet(2, "Test", "test/"),
      new ImageSet(3, "Extra", "extra/")
   };

   private final ImageSetRepo imageSetRepo;

   public ImageSetRepo getImageSetRepo() {
      return imageSetRepo;
   }

   @Autowired
   public ImageSetService(ImageSetRepo imageSetRepo) {
      this.imageSetRepo = imageSetRepo;
   }



   public void populate() {
      for(int i = 0; i< DefaultImageSets.length; ++i) {
         imageSetRepo.save( DefaultImageSets[i] );
      }
   }

   public void populateIfEmpty() {
      if( imageSetRepo.count() == 0 ) {
         populate();;
      }
   }

   @Override
   public void afterPropertiesSet() throws Exception {
      populateIfEmpty();
   }
}
