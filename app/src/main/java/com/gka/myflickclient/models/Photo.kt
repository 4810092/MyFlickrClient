package com.gka.myflickclient.models

class Photo {

    var id: String? = null
    //    var owner: String? = null
    var secret: String? = null
    var server: String? = null
    var farm: Int = 0
    var title: String? = null
//    var ispublic: Int = 0
//    var isfriend: Int = 0
//    var isfamily: Int = 0


    val thumb: String
        get() = "https://farm$farm.staticflickr.com/$server/${id}_${secret}_m.jpg"


    val photo: String
        get() = "https://farm$farm.staticflickr.com/$server/${id}_${secret}_c.jpg"


}
