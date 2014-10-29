package com.automattic.simplenote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.automattic.simplenote.utils.ThemeUtils;
import com.google.analytics.tracking.android.EasyTracker;

public class NoteEditorActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ThemeUtils.setTheme(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_editor);

        EasyTracker.getInstance().activityStart(this);

        // No title, please.
        setTitle("");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NoteEditorFragment noteEditorFragment;
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            // Create the note editor fragment
            Bundle arguments = new Bundle();
            arguments.putString(NoteEditorFragment.ARG_ITEM_ID,
                    intent.getStringExtra(NoteEditorFragment.ARG_ITEM_ID));
            arguments.putBoolean(NoteEditorFragment.ARG_NEW_NOTE,
                    intent.getBooleanExtra(NoteEditorFragment.ARG_NEW_NOTE, false));
            if (intent.hasExtra(NoteEditorFragment.ARG_MATCH_OFFSETS))
                arguments.putString(NoteEditorFragment.ARG_MATCH_OFFSETS,
                    intent.getStringExtra(NoteEditorFragment.ARG_MATCH_OFFSETS));

            noteEditorFragment = new NoteEditorFragment();
            noteEditorFragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .add(R.id.note_editor_container, noteEditorFragment, NotesActivity.TAG_NOTE_EDITOR)
                    .commit();
        }
    }
}
