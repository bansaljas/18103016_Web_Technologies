var christmasTree = function(n)
{
    for(var i=1; i<=n; i++)
    {
        var pattern = "";
        for(var j=i; j<n; j++)
            pattern+=" ";

        for(var j=1; j<=(2*i-1); j++)
        {
            if(i === 1) pattern+="*";
            else pattern+="0";
        }
        console.log(pattern);
    }
};

var n = prompt("Enter the height of the tree(should be greater than equal to 1): " );
christmasTree(n);
