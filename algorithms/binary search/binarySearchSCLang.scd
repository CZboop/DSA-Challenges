// trying out binary search in sclang

// hard coding array for now
x = [1, 4, 5, 44, 90, 123, 154, 208, 333, 666, 777, 10001, 200000];
y = 777;


// actual binary search function
f = {arg x, target;
	// setting bounds of search indexes here
	var lower = 0;
	var upper = x.size() - 1;

	while ({lower <= upper}, {
		//  p is var for split index, using single letter and tilde to make global
		// p since s is reserved for server
		// bit long winded but div to ensure whole number division
		~p = lower + (upper.div(lower).div(2));
		if (x.at(~p) == target, {
			// breaking out of while loop, should revisit if better way to do this
			lower = lower + x.size();
		},
		{
			// moving bounds based on whether split higher or lower than target
			if (x.at(~p) < target, {
				lower = ~p + 1;
			},
			{
				upper = ~p - 1;
			}
		)})
	});
	// sclang doesn't have explcit return instead putting variable at end of function
	~p;
};

// running function, will see result in post window
f.value(x, y);

