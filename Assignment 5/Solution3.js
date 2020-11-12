var map = { "(": ")", "[": "]", "{": "}" }

var isValid = function(s)
{
    var stack = [];
    for (var i = 0; i < s.length; i++)
    {
        var item = s[i];
        if (map[item]) stack.push(map[item]);
        else if (item !== stack.pop()) return false;
    }

    return stack.length === 0;
};

var s = prompt("Enter the string to be verified.");
isValid(s);
