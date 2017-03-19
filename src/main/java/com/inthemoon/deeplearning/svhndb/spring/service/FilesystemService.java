package com.inthemoon.deeplearning.svhndb.spring.service;

import com.inthemoon.deeplearning.svhndb.model.ImageSet;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Dims on 10.03.2017.
 */
@Component
public class FilesystemService implements InitializingBean{

   @Value("${CORPUS_ROOT_VARIABLE}")
   private String corpusRootVariable;

   public String getCorpusRootVariable() {
      return corpusRootVariable;
   }




   private File corpusRoot;

   private void initializeCorpusRoot() {
      String rootString = System.getenv(corpusRootVariable);
      corpusRoot = new File(rootString);
      if( !corpusRoot.isDirectory() ) {
         throw new IllegalStateException("Corpus root directory does not exist");
      }
   }

   public File getCorpusRoot() {
      return corpusRoot;
   }

   public String getPathRelativeToCorpusRoot(File file) {
      File base = getCorpusRoot();
      return base.toURI().relativize(file.toURI()).getPath();
   }




   public File getImageSetRoot(ImageSet imageSet) {
      return new File( getCorpusRoot(), imageSet.getPath() );
   }

   public Iterable<File> getImageSetImageFiles(ImageSet imageSet) {
      File file = getImageSetRoot(imageSet);
      try {
         Stream<File> stream = Files
            .list(file.toPath())
            .filter(path -> path.toString().toLowerCase().endsWith(".png"))
            .map(Path::toFile);

         Iterable<File> ans = stream::iterator;
         return ans;

      } catch (IOException e) {
         throw new RuntimeException(e);
      }

   }

   public File getDigitStructFile(ImageSet imageSet) {
      return new File( getImageSetRoot(imageSet), "digitStruct.mat");
   }


   public int[] getImageWidthAndHeight(File imageFile) {
      try(ImageInputStream in = ImageIO.createImageInputStream(imageFile)){
         if (in != null) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
            if (readers != null && readers.hasNext()) {
               ImageReader reader = readers.next();
               reader.setInput(in);
               int width = reader.getWidth(0);
               int height = reader.getHeight(0);
               return new int[] {width, height};
            }
         }
      }
      catch (IOException e) {
         throw new RuntimeException(e);
      }
      throw new RuntimeException();
   }


   @Override
   public void afterPropertiesSet() throws Exception {
      initializeCorpusRoot();
   }
}
