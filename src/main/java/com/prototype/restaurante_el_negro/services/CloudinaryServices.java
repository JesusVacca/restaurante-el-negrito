package com.prototype.restaurante_el_negro.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Service
public class CloudinaryServices {
    private final Cloudinary cloudinary;
    public CloudinaryServices(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public Map uploadImage(MultipartFile file) throws IOException {
        return this.cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type","image",
                        "asset_folder","restaurante"
                )
        );
    }
    public Map deleteImage(String public_id) throws Exception {
        return this.cloudinary.api().deleteResources(
                Arrays.asList(public_id),
                ObjectUtils.asMap(
                        "type","upload",
                        "resource_type","image"
                )
        );
    }
}
