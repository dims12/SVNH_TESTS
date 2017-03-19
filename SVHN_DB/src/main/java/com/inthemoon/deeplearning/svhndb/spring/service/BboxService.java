package com.inthemoon.deeplearning.svhndb.spring.service;

import ch.systemsx.cisd.base.mdarray.MDArray;
import ch.systemsx.cisd.hdf5.HDF5Factory;
import ch.systemsx.cisd.hdf5.IHDF5Reader;
import ch.systemsx.cisd.hdf5.IHDF5SimpleReader;
import com.inthemoon.deeplearning.svhndb.model.Bbox;
import com.inthemoon.deeplearning.svhndb.model.ImageSet;
import com.inthemoon.deeplearning.svhndb.repo.BboxRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Created by Dims on 11.03.2017.
 */
@Component
public class BboxService {

   private final FilesystemService filesystemService;

   private final ImageSetService imageSetService;

   private final BboxRepo bboxRepo;

   @Autowired
   public BboxService(FilesystemService filesystemService, ImageSetService imageSetService, BboxRepo bboxRepo) {
      this.filesystemService = filesystemService;
      this.imageSetService = imageSetService;
      this.bboxRepo = bboxRepo;
   }



   public Map<String, List<Bbox>> loadDigitStruct(ImageSet imageSet) {


      try {
         File digitStructFile = filesystemService.getDigitStructFile(imageSet);
         IHDF5Reader reader = HDF5Factory.openForReading(digitStructFile.getAbsoluteFile());
         MDArray<String> array = reader.reference().readMDArray("/digitStruct/name", false);
         System.out.println(reader.toString());
         return null;

      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public void populateBbox() {

      bboxRepo.deleteAll();

      for(ImageSet imageSet : imageSetService.getImageSetRepo().findAll()) {
         Map<String, List<Bbox>> digitStruct = loadDigitStruct(imageSet);
      }
   }

   public void populateBboxIfEmpty() {
      if( bboxRepo.count() == 0 ) {
         populateBbox();
      }
   }

}
