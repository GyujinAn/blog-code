#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>
#include <cstdlib>
#include <cctype>

using namespace std;

enum TokenKind
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
    Identifier,
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
    TokenKind kind;
    string text;
    int intValue;

    Token()
    {
        kind = Others;
        text = ";";
        intValue = 0;
    }
    Token(TokenKind k, const string &s, int d = 0)
    {
        kind = k;
        text = s;
        intValue = d;
    }
};

void initChType();
Token nextToken();
int nextCh();
bool isOperator2(int c1, int c2);
TokenKind getKind(const string &s);

TokenKind charType[256];
Token token;
ifstream fin;

struct Keyword
{
    const char *keyName;
    TokenKind keyKind;
};

Keyword KeywordTable[] = {
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

    cout << "text     kind intVal\n";
    initChType();

    for (token = nextToken(); token.kind != EndOfToken; token = nextToken())
    {
        cout << left << setw(10) << token.text
             << right << setw(3) << token.kind
             << " " << token.intValue << endl;
    }

    return 0;
}

void initChType()
{
    int i;

    for (i = 0; i < 256; i++)
    {
        charType[i] = Others;
    }

    for (i = '0'; i <= '9'; i++)
    {
        charType[i] = Digit;
    }

    for (i = 'A'; i <= 'Z'; i++)
    {
        charType[i] = Letter;
    }

    for (i = 'a'; i <= 'z'; i++)
    {
        charType[i] = Letter;
    }

    charType['('] = LeftParenthesis;
    charType[')'] = RightParenthesis;
    charType['<'] = Less;
    charType['>'] = Great;
    charType['+'] = Plus;
    charType['-'] = Minus;
    charType['*'] = Multi;
    charType['/'] = Divi;
    charType['_'] = Letter;
    charType['='] = Assign;
    charType[','] = Comma;
    charType['"'] = DoubleQuote;

    // for (i = 0; i < 256; i++)
    // {
    //     cout << "index: " << i << " type: " << ctyp[i] << endl;
    // }
}

Token nextToken()
{
    TokenKind kind;
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

    switch (charType[ch])
    {
    case Letter:
        for (; charType[ch] == Letter || charType[ch] == Digit; ch = nextCh())
        {
            txt += ch;
        }
        break;
    case Digit:
        for (num = 0; charType[ch] == Digit; ch = nextCh())
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
        if (isOperator2(ch0, ch))
        {
            txt += ch;
            ch = nextCh();
        }
    }

    kind = getKind(txt);
    if (kind == Others)
    {
        cout << "incorrect token: " << txt << endl;
        exit(1);
    }
    return Token(kind, txt);
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

bool isOperator2(int c1, int c2)
{
    char s[] = "  ";
    if (c1 == '\0' || c2 == '\0')
    {
        return false;
    }
    s[1] = c1;
    s[2] = c2;
    return strstr(" <= >= == != ", s) != NULL;
}

TokenKind getKind(const string &s)
{
    for (int i = 0; KeywordTable[i].keyKind != END_list; i++)
    {
        if (s == KeywordTable[i].keyName)
        {
            return KeywordTable[i].keyKind;
        }
    }
    if (charType[s[0]] == Letter)
    {
        return Identifier;
    }
    if (charType[s[0] == Digit])
    {
        return IntegerNumber;
    }
    return Others;
}