package com.example.music_application

data class MusicData(
    val `data`: List<Data>,
    val next: String,
    val total: Int
)