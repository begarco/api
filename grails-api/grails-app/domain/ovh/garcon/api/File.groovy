package ovh.garcon.api

class File {

    String name
    String type
    Date creation
    String url
    static belongsTo = [owner: Contributor]
    static hasMany = [readers: Contributor]

    static constraints = {
        readers nullable: true
    }
}
