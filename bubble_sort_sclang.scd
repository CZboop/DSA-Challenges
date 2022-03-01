// trying bubble sort in sclang

// setup and creating array of random numbers
"starting".postln;
y = 10;
z = 100;
x = Array.fill(y, { arg i; z.rand });

// intialising swap counter to negative for first run of loop
~c = -1;

// defining the sorting function
f = {arg x;
	~c = 0;
	// penultimate index since checking against next elem
	~p = x.size - 1;
// iterating over items in array
x.do({ arg item, ind;
	// excluding last element
	if (ind < ~p){
		// evaluating whether to swap
		if (x.at(ind) > x.at(ind + 1)) {
			// swapping
			var n1 = x[ind];
			var n2 = x[ind +1];
			x[ind + 1] = n1;
			x[ind] = n2;
			// printing to see progress - note this is each swap not each loop of array
			x.postln;
			// swap counter for early stopping
			~c = ~c+1;
		};
	};
});
};

// calling the sorting function - will go until there were no swaps
(
for (1, y,  {if (~c!=0){f.value(x)};});
);

// printing to verify not stuck in a loop etc.
"ending".postln;
