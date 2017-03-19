package com.inthemoon.deeplearning.svhndb.repo;

import com.inthemoon.deeplearning.svhndb.model.Image;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dims on 10.03.2017.
 */
public interface ImageRepo extends CrudRepository<Image, Long> {
}
