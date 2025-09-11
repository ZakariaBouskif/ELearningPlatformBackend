package com.elearningplatform.request;


import jakarta.validation.constraints.NotBlank;

public record CategoryCourseRequest(
        @NotBlank String title
) {}