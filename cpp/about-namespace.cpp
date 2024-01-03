#include <iostream>

namespace A
{
    void SimpleFunc(void);

    void HelloWorld(void);
}

namespace B
{
    void SimpleFunc(void);
}

void A::SimpleFunc(void)
{
    HelloWorld();
    std::cout << "Hello A" << std::endl;
}

void A::HelloWorld(void)
{
    std::cout << "Hello World" << std::endl;
}

void B::SimpleFunc(void)
{
    std::cout << "Hello B" << std::endl;
}

int main(void)
{
    A::SimpleFunc();
    B::SimpleFunc();

    using A::HelloWorld;

    HelloWorld();

    return 0;
}