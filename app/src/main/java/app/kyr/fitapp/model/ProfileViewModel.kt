package app.kyr.fitapp.model


class ProfileViewModel {
    var name: String = ""
    var age: String = ""
    var bio: String = ""

    fun saveProfileData(name: String, username: String, bio: String) {
        this.name = name
        this.age = username
        this.bio = bio
    }

}
