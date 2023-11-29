package com.example.homeworkthireen

data class Field(
    var field_id: Int,
    var hint: String,
    var field_type: String,
    var keyboard: String?,
    var required: Boolean,
    var is_active: Boolean,
    var icon: String,
    var enteredText: String?
)


