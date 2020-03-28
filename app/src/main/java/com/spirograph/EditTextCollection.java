package com.spirograph;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class EditTextCollection {

    public static List<EditText> editTextList = new ArrayList<>();

    public static int addEditText(
            Context context,
            int numberOfLines,
            LinearLayout dynamicEditTextsLayout
    ) {
        EditText editText = new EditText(context);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        editText.setLayoutParams(p);
        editText.setId(numberOfLines + 1);
        dynamicEditTextsLayout.addView(editText);
        editTextList.add(editText);
        numberOfLines++;
        return numberOfLines;
    }

    public static List<Integer> getLengths() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < editTextList.size(); i++) {
            String text = editTextList.get(i).getText().toString();

            result.add(Integer.parseInt(editTextList.get(i).getText().toString()));
        }
        return result;
    }


    public static void clear() {
        editTextList = new ArrayList<>();
    }
}