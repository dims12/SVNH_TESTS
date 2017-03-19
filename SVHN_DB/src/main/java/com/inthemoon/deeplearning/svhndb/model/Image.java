package com.inthemoon.deeplearning.svhndb.model;

import javax.persistence.*;

/**
 * Created by Dims on 10.03.2017.
 */
@Entity(name = "image")
public class Image {


   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }


   @ManyToOne
   @JoinColumn(name = "image_set_id", nullable = false)
   private ImageSet imageSet;

   public ImageSet getImageSet() {
      return imageSet;
   }

   public void setImageSet(ImageSet imageSet) {
      this.imageSet = imageSet;
   }





   @Column(name = "path", nullable = false)
   private String path;

   public String getPath() {
      return path;
   }

   public void setPath(String path) {
      this.path = path;
   }



   @Column(name = "width")
   private Integer width;

   public Integer getWidth() {
      return width;
   }

   public void setWidth(Integer  width) {
      this.width = width;
   }


   @Column(name = "height")
   private Integer height;

   public Integer getHeight() {
      return height;
   }

   public void setHeight(Integer height) {
      this.height = height;
   }


   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(getPath());
      sb.append(": ");
      sb.append("[");
      sb.append(getWidth());
      sb.append(" x ");
      sb.append(getHeight());
      sb.append("]");
      return sb.toString();
   }
}
