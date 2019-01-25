package com.evacipated.cardcrawl.mod.bard.helpers;

import com.evacipated.cardcrawl.mod.bard.melodies.AbstractMelody;
import com.evacipated.cardcrawl.mod.bard.notes.AbstractNote;

import java.util.*;

public class MelodyManager
{
    private static List<AbstractNote> allNotes = new ArrayList<>();
    private static Map<String, AbstractNote> notes = new HashMap<>();
    private static List<AbstractMelody> melodies = new ArrayList<>();

    public static void addNote(AbstractNote note)
    {
        allNotes.add(note);
        notes.put(note.name() + " Note", note);
        notes.put("[" + note.name() + "Note]", note);
    }

    public static AbstractNote getNote(String key)
    {
        return notes.get(key);
    }

    public static List<AbstractNote> getAllNotes()
    {
        return new ArrayList<>(allNotes);
    }

    public static void addMelody(AbstractMelody melody)
    {
        /*
        for (AbstractMelody m : melodies) {
            if (m.conflictsMelody(melody)) {
                BardMod.logger.error(melody + " conflicts with existing " + m);
                throw new RuntimeException(melody + " conflicts with existing " + m);
            }
        }
        //*/
        melodies.add(melody);
    }

    public static List<AbstractMelody> getAllMelodies()
    {
        return melodies;
    }

    public static AbstractMelody getMelodyFromNotes(List<AbstractNote> notes)
    {
        for (AbstractMelody melody : melodies) {
            if (melody.matchesNotes(notes)) {
                return melody.makeCopy();
            }
        }
        return null;
    }

    public static List<AbstractMelody> getAllMelodiesFromNotes(List<AbstractNote> notes)
    {
        List<AbstractMelody> ret = new ArrayList<>();
        for (AbstractMelody melody : melodies) {
            int idx = melody.endIndexOf(notes);
            if (idx != -1) {
                ret.add(melody.makeCopy());
            }
        }
        return ret;
    }
}
