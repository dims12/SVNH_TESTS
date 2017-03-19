package com.inthemoon.deeplearning.svhndb.model;

import javax.persistence.*;

/**
 * Created by Dims on 10.03.2017.
 */
@Entity
@Table(name="image_set")
public class ImageSet {

   @Id
   @Column(name = "id")
   private long id;

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }





   @Column(name = "name", unique = true, nullable = false)
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }


   @Column(name = "path", unique = true, nullable = false)
   private String path;

   public String getPath() {
      return path;
   }

   public void setPath(String path) {
      this.path = path;
   }



   public ImageSet() {
   }

   public ImageSet(long id, String name, String path) {
      this.id = id;
      this.name = name;
      this.path = path;
   }
}
