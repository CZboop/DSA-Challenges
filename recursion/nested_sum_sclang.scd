// defining sum nested list function
l = {arg x, y;
	x.do({arg item, ind;
		if ( item.isKindOf(Integer),
			{y.add(item)},
			{l.value(item, y)}
	)}
	);
	y.reduce('+');
};

// making nested list
x = Array.fill(2, { arg i; Array.fill(3, {arg j; Array.fill(5, { arg k; Array.fill(2, { arg l; 10.rand }) })})});
y = List.new();

// calling sum nested list func
l.value(x, y).postln;