package com.petstore

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.serialization.json
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
  val server = embeddedServer(Netty, port = 8080) {

    install(ContentNegotiation) {
      json()
    }

    getPetRouting()
    addPetRouting()
    adoptPetRouting()
  }
  server.start(wait = true)
}

fun Application.getPetRouting() {
  routing {
    get {
      call.respond(petList)
    }
    get("/{id}") {
      val id = call.parameters["id"]?.toInt() ?: error("Invalid id provided")
      val pet = petList.find { it.id == id }

      call.respond(pet ?: "Sorry no pet with given Id found")
    }
  }
}

fun Application.addPetRouting() {
  routing {
    post {
      val newPet = call.receive<Pet>()
      petList.add(newPet)

      call.respond("${newPet.name} add to the store!")
    }
  }
}

fun Application.adoptPetRouting() {
  routing {
    delete("/{id}") {
      val id = call.parameters["id"]?.toInt() ?: error("Invalid id provided")
      val pet = petList.find { it.id == id }

      pet?.let {
        petList.remove(pet)
        call.respond("${pet.name} has been adopted")
      }

      call.respond("Sorry no pet with given Id found")
    }
  }
}

val petList = mutableListOf(
  Pet("Snowy", "Dog", 10.0),
  Pet("Oscar", "Cat", 8.0),
  Pet("Charlie", "Turtle", 12.0)
)