package Entity

import java.lang.annotation.Documented

@Documented
case class Users(
                id:String,
                name : String,
                age :Int,
                branch:String

                )
