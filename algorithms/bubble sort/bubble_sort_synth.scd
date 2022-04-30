// bubble sort in synths??

s.boot;

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

			// swap counter for early stopping
			~c = ~c+1;
				// making a list of the array history across sorts to loop through and use in synth
				h.add(x);
				h.postln;
				h = h.flat;
		};
	};
});
};
//

// history list
h = List.new();
// setting up random array of ints
y = 20;
z = 10;
x = Array.fill(y, { arg i; z.rand });


// an example of just using the whole array in a synth as it is being sorted
// {FSinOsc.ar(x)}.play;

// running the sort
(
for (1, y,  {if (~c!=0){f.value(x);}});
);


// defining a routine to enable waiting and manage the synth
Routine({
	// looping through the history list
	for(0, h.size -1, {arg i;
		// making synthdef
SynthDef( \sortSynth, { | amp = 0.02, freq = 253, trig = 1 |
    var env, sig;
    env = EnvGen.kr( Env.asr( 0.001, 0.9, 0.001 ), trig, doneAction: 0 );
    sig = LFTri.ar( [ freq, freq * 1.099 ], 1.0, amp ) * env;
    Out.ar( [ 0 ], sig * 0.6 );
}).add;
		// synth from synthdef
t = Synth( \sortSynth, {SinOsc.ar(freq: 400, phase: 1.5, mul: 0.5 )});
		// updating synth with list info, currently just frequency
		o = OSCFunc( { t.set( \freq, LinLin.kr(h.at(i), 1, 10, 400,1000), \mul, LinLin.kr(10, 1, 100, 0,100), \amp, LinLin.kr(40, 100, 470, 2,5) );
}, "/sortSynth" );
		// slight wait at each stage so that changes are clearer
		wait(0.2);
	})
}).play;

// to enable recording the audio output
s.makeWindow;