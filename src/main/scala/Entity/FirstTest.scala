//package Entity
//
//
//import akka.actor.typed.ActorSystem
//import akka.actor.typed.scaladsl.Behaviors
//import akka.http.scaladsl.Http
//import akka.http.scaladsl.model._
//import akka.http.scaladsl.server.Directives._
//import akka.http.scaladsl.server.Directives.{complete, entity, path}
//import Entity.Users._
//import Services.UserServices
//
//import scala.concurrent.Await
//import scala.concurrent.ExecutionContext.Implicits.global
//import scala.concurrent.duration.Duration
//// spray json marsshalling
//import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
//import spray.json.DefaultJsonProtocol._
//
//object FirstTest extends App {
//println("starting the application")
//
//
////  val uri = "mongodb://localhost:27017"
////  val DATABASE="mydb"
////  val connection = MongoClient(uri)
////  val database = connection.getDatabase(DATABASE)
////  database.createCollection("testTable")
////  val collection:MongoCollection[Document]=database.getCollection("testTable")
////
////  collection.drop().results()
////
////  println(database)
////  println(collection)
//
//
////  case class TimeForProject(project: String, subProject: String, time: Long, updated: Date)
////
////
//// val obj =TimeForProject("firstProject","firstSubProject",23,new Date())
//
//  //  toFuture use kro ........
//  // flash api
//
////  val doc: Document = Document("_id" -> 0, "name" -> "MongoDB", "type" -> "database",
////    "count" -> 1, "info" -> Document("x" -> 203, "y" -> 102))
////
////  collection.insertOne(doc).printResults()   // for inserting one document
////  collection.find.first().printResults()    // for getting the first row of data
////
////  val documents:Seq[Document]= (1 to 100) map{i:Int=> Document("i"->i)}
////  val insertObservable = collection.insertMany(documents)
////
////  val insertAndCount = for {
////    insertResult <- insertObservable
////    countResult <- collection.countDocuments()
////  } yield countResult
////
////  println(s"total # of documents after inserting 100 small ones :  ${insertAndCount.headResult()}")
//
//
//
//implicit val actorSystem=ActorSystem(Behaviors.empty,"akka-http")
//// implicit val newUser:spray.json.RootJsonFormat[Users]=jsonFormat3(Users.apply)
//
//  private val userServices=new UserServices()
//
//  val createUser= get  {
//    path("addUser") {
//        entity(as[Users]){
//          newUser=> {
//            val res=userServices.addUser(newUser)
//            Await.result(res, Duration.Inf)
//            println("here is the data: " + res)
//            complete(HttpEntity("Data saved successfully"))
//          }
//        }
////         val res=userServices.addUser(2,"gopal",20,"CSE")
////          Await.result(res,Duration.Inf)
////          println("here is the data: "+res)
//
//
//      }
//
//    }
//
//  val getUser=get {
//    path("getUser"){
//
//      val res= userServices.getTheUser(2)
//      println("data we get is :",res)
//      complete((HttpEntity("got the data")))
//    }
//  }
//
//
//
//
//  Http().newServerAt("127.0.0.1",8080).bind(concat(createUser,getUser))
//
//
//
//
//
////   val customCodecs = fromProviders(classOf[Users])
////   val codecRegistry = fromRegistries(customCodecs, DEFAULT_CODEC_REGISTRY)
////  val myuserDB=database.getCollection[Users](collectionName).withCodecRegistry(codecRegistry)
////
////
////
////
////
////  myuserDB.drop().results()
////
////  val ins=addUser(1,"Rohit",24,"ECE")
////  Await.result(ins,Duration.Inf)
////
////  println("after insertion "+ins)
////
////
////  val data=myuserDB.find(and(equal("id", 1))).toFuture()
////  val res=Await.result(data,Duration.Inf)
////  println("here is my data : "+data)
//
//
//
//}
