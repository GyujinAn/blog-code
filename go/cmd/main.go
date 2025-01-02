package main

import (
	"log"

	"go_playground/pkg"
)

func main() {
	log.Println("hello world")

	hello := new(pkg.MyStruct)

	log.Println(hello)

	hello.Init("test", 10)

	log.Println(hello)
}
