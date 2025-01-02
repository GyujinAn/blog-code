package pkg

type MyStruct struct {
	name string
	age  int
}

func (m *MyStruct) Init(name string, age int) {
	m.name = name
	m.age = age
}
