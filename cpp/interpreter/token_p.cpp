#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>
#include <cstdlib>
#include <cctype>

using namespace std;

enum TknKind
{
    LeftParenthesis = 1,
    RightParenthesis,
    Plus,
    Minus,
    Multi,
    Divi,
    Assign,
    Comma,
    DoubleQuote,
    Equal,
    NotEqual,
    Less,
    LessEqual,
    Great,
    GreatEqual,
    If,
    Else,
    End,
    Print,
    Ident,
    IntegerNumber,
    String,
    Letter,
    Digit,
    EndOfToken,
    Others,
    END_list
};

struct Token
{
    TknKind kind;
    string text;
    int intVal;

    Token()
    {
        kind = Others;
        text = ";";
        intVal = 0;
    }
    Token(TknKind k, const string &s, int d = 0)
    {
        kind = k;
        text = s;
        intVal = d;
    }
};

void initChTyp();
Token nextTkn();
int nextCh();
bool is_ope2(int c1, int c2);
TknKind get_kind(const string &s);

TknKind ctyp[256];
Token token;
ifstream fin;

struct KeyWord
{
    const char *keyName;
    TknKind keyKind;
};

KeyWord KeyWdTbl[] = {
    {"if", If},
    {"else", Else},
    {"end", End},
    {"print", Print},
    {"(", LeftParenthesis},
    {")", RightParenthesis},
    {"+", Plus},
    {"-", Minus},
    {"=", Assign},
    {",", Comma},
    {"==", Equal},
    {"!=", NotEqual},
    {"<", Less},
    {"<=", LessEqual},
    {">", Great},
    {">=", GreatEqual},
    {"", END_list},
};

int main(int argc, char *argv[])
{
    cout << END_list << endl;
    cout << "The Command:" << argv[0] << endl;
    cout << "The first argument:" << argv[1] << endl;

    if (argc == 1)
    {
        cout << "no arguments! \n";
        exit(1);
    }

    fin.open(argv[1]);
    if (!fin)
    {
        cout << "cannot open the file! \n";
        exit(1);
    }

    cout << "text   kind intVal\n";
    initChTyp();

    // for (token = nextTkn(); token.kind != EofTkn; token = nextTkn())
    // {
    // }

    return 0;
}

void initChTyp()
{
    int i;

    for (i = 0; i < 256; i++)
    {
        ctyp[i] = Others;
    }

    for (i = '0'; i <= '9'; i++)
    {
        ctyp[i] = Digit;
    }

    for (i = 'A'; i <= 'Z'; i++)
    {
        ctyp[i] = Letter;
    }

    for (i = 'a'; i <= 'z'; i++)
    {
        ctyp[i] = Letter;
    }

    ctyp['('] = LeftParenthesis;
    ctyp[')'] = RightParenthesis;
    ctyp['<'] = Less;
    ctyp['>'] = Great;
    ctyp['+'] = Plus;
    ctyp['-'] = Minus;
    ctyp['*'] = Multi;
    ctyp['/'] = Divi;
    ctyp['_'] = Letter;
    ctyp['='] = Assign;
    ctyp[','] = Comma;
    ctyp['"'] = DoubleQuote;

    // for (i = 0; i < 256; i++)
    // {
    //     cout << "index: " << i << " type: " << ctyp[i] << endl;
    // }
}

Token nextTkn()
{
    TknKind kd;
    int ch0, num = 0;
    static int ch = ' ';
    string txt = "";

    while (isspace(ch))
    {
        ch = nextCh();
    }

    if (ch == EOF)
    {
        return Token(EndOfToken, txt);
    }

    switch (ctyp[ch])
    {
    case Letter:
        for (; ctyp[ch] == Letter || ctyp[ch] == Digit; ch == nextCh())
        {
            txt += ch;
        }
        break;
    case Digit:
        for (num = 0; ctyp[ch] == Digit; ch = nextCh())
        {
            num = num * 10 + (ch - '0');
        }
        return Token(IntegerNumber, txt, num);
    case DoubleQuote:
        for (ch = nextCh(); ch != EOF && ch != '\n' && ch != '"'; ch = nextCh())
        {
            txt += ch;
        }
        if (ch != '"')
        {
            cout << "string literal should be closed \n";
            exit(1);
        }
        ch = nextCh();
        return Token(String, txt);
    default:
        txt += ch;
        ch0 = ch;
        ch = nextCh();
    }

    kd = get_kind(txt);
    if (kd == Others)
    {
        cout << "incorrect token: " << txt << endl;
        exit(1);
    }
    return Token(kd, txt);
}

int nextCh()
{
    static int c = 0;
    if (c == EOF)
    {
        return c;
    }
    if ((c = fin.get()) == EOF)
    {
        fin.close();
    }
    return c;
}