#define PI 3.14
#define PRODUCT(X, Y) ((X) * (Y))
#define CIRCLE_AREA(R) (PRODUCT((R), (R)) * PI)

void MacroUseMacro()
{
    PI;

    PRODUCT(3, 3);

    CIRCLE_AREA(5);
}

#define ADD 1
#define MIN 0

void IfEndif()
{
    int a = 3;
    int b = 5;
    int result;
#if ADD
    result = a + b;
#endif

#if MIN
    result = a - b;
#endif
}

// #define ADD_ 1
#define MIN_ 0

void IfdefEndif()
{
    int a = 3;
    int b = 5;
    int result;
#ifdef ADD_
    result = a + b;
#endif

#ifdef MIN_
    result = a - b;
#endif
}

#define NUM 6

void ElseEndif()
{
#if NUM == 5
    int num5;
#elif NUM == 6
    int num6;
#elif NUM == 7
    int num7;
#else
    int notnum;
#endif
}

#define STRING_JOB(A, B) #A "의 직업은" #B "입니다."

void MacroOpToString()
{
    char *str1 = STRING_JOB(jin, developer);
    char *str2 = STRING_JOB(jason, engineer);
}

#define STNUM(Y, S, P) Y##S##P

void UnivStdNum()
{
    int num = STNUM(10, 65, 175);
}