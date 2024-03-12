package com.microBlog.blogApplication.payload;



import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data

public class PostDto {


        private String id;
        @NotEmpty
        @Size(min = 4, message ="Post title should have at least 4 characters")
        private String title;
        @NotEmpty
        @Size(min = 10, message ="Post description should have at least 10 characters")
        private String description;

        @NotEmpty
        @Size(min = 2, message ="Post content should have at least 2 characters")
        private String content;

}
