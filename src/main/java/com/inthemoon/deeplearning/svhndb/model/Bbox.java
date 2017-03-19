package com.inthemoon.deeplearning.svhndb.model;

import javax.persistence.*;

/**
 * Created by Dims on 11.03.2017.
 */
@Entity
public class Bbox {

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
   @JoinColumn(name = "image_id", nullable = false)
   private Image image;

   public Image getImage() {
      return image;
   }

   public void setImage(Image image) {
      this.image = image;
   }




   @Column
   private Integer ord;

   public Integer getOrd() {
      return ord;
   }

   public void setOrd(Integer ord) {
      this.ord = ord;
   }


   @Column
   private Integer left;

   public Integer getLeft() {
      return left;
   }

   public void setLeft(Integer left) {
      this.left = left;
   }





   @Column
   private Integer top;

   public Integer getTop() {
      return top;
   }

   public void setTop(Integer top) {
      this.top = top;
   }




   @Column
   private Integer width;

   public Integer getWidth() {
      return width;
   }

   public void setWidth(Integer width) {
      this.width = width;
   }



   @Column
   private Integer height;

   public Integer getHeight() {
      return height;
   }

   public void setHeight(Integer height) {
      this.height = height;
   }



   @Column
   private Integer label;

   public Integer getLabel() {
      return label;
   }

   public void setLabel(Integer label) {
      this.label = label;
   }
}
