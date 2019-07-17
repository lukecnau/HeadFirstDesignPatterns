package beat;

import java.util.ArrayList;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;


public class BeatModel implements BeatModelInterface {
	Sequencer sequencer;
	ArrayList<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
	ArrayList<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
	Track track;
	Sequence sequence;
	int bpm = 90;
	
	@Override
	public void initialize() {
		PrintLog.ShowThreadID("BeatModel.initialize()");
		setUpMidi();
		buildTrackAndStart();
	}

	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addMetaEventListener(new MetaEventListener() {
		        public void meta(MetaMessage msg) {
		    		System.out.printf("BeatModel.meta() -> metaType = %d\n",msg.getType());
		    		if( msg.getType() == 47) {
		    			beatEvent();
		    			sequencer.start();
		    			setBPM(getBPM());
		    		}
		        }
		    });
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(getBPM());
			sequencer.setLoopCount(1);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void buildTrackAndStart() {
		int[] trackList = {35, 0, 46, 0};
		
		sequence.deleteTrack(null);
		track = sequence.createTrack();
		
		makeTracks(trackList);
		track.add(makeEvent(192,9,1,0,4));
		try {
			sequencer.setSequence(sequence);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void makeTracks(int[] list) {
		for(int i = 0; i < list.length; ++i) {
			int key = list[i];
			
			if ( key != 0 ) {
				track.add(makeEvent(144,9,key,100,i));
				track.add(makeEvent(128,9,key,100,i+1));
			}
		}
	}
	
	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return event;
	}
	
	@Override
	public void on() {
		sequencer.start();
		setBPM(getBPM());
	}

	@Override
	public void off() {
		setBPM(getBPM());
		sequencer.stop();
	}

	@Override
	public void release() {
		while( sequencer.isRunning() )
			sequencer.stop();
		
		while( sequencer.isOpen() )
			sequencer.close();
	}
	
	@Override
	public void setBPM(int bpm) {
		this.bpm = bpm;
		System.out.printf("BeatModel.setBPM() -> new bpm = %d\n",this.bpm);
		sequencer.setTempoInBPM(getBPM());
		notifyBPMObservers();
	}

	public void notifyBPMObservers() {
		for(int i = 0; i< bpmObservers.size(); ++i) {
			BPMObserver o = (BPMObserver)bpmObservers.get(i);
			o.updateBPM();
		}
	}
	
	@Override
	public int getBPM() {
//		System.out.printf("model.getBPM = %d\n", this.bpm);
		return this.bpm;
	}

	void beatEvent() {
		notifyBeatObservers();
	}
	
	public void notifyBeatObservers() {
		for(int i = 0; i < beatObservers.size(); ++i) {
			BeatObserver o = (BeatObserver)beatObservers.get(i);
			o.updateBeat();
		}
	}
	
	@Override
	public void registerObserver(BeatObserver o) {
		beatObservers.add(o);
	}

	@Override
	public void removeObserver(BeatObserver o) {
		int i = beatObservers.indexOf(o);
		if( i >= 0)
			beatObservers.remove(i);
	}

	@Override
	public void registerObserver(BPMObserver o) {
		bpmObservers.add(o);
	}

	@Override
	public void removeObserver(BPMObserver o) {
		int i = bpmObservers.indexOf(o);
		if( i >= 0)
			bpmObservers.remove(i);
	}

}
