// Generated by view binder compiler. Do not edit!
package com.example.kedwi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.kedwi.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCreateGenderBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton backBtn;

  @NonNull
  public final TextView createNameHead;

  @NonNull
  public final Button female;

  @NonNull
  public final Button male;

  @NonNull
  public final Button noAsk;

  private FragmentCreateGenderBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageButton backBtn, @NonNull TextView createNameHead, @NonNull Button female,
      @NonNull Button male, @NonNull Button noAsk) {
    this.rootView = rootView;
    this.backBtn = backBtn;
    this.createNameHead = createNameHead;
    this.female = female;
    this.male = male;
    this.noAsk = noAsk;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCreateGenderBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCreateGenderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_create_gender, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCreateGenderBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backBtn;
      ImageButton backBtn = ViewBindings.findChildViewById(rootView, id);
      if (backBtn == null) {
        break missingId;
      }

      id = R.id.createNameHead;
      TextView createNameHead = ViewBindings.findChildViewById(rootView, id);
      if (createNameHead == null) {
        break missingId;
      }

      id = R.id.female;
      Button female = ViewBindings.findChildViewById(rootView, id);
      if (female == null) {
        break missingId;
      }

      id = R.id.male;
      Button male = ViewBindings.findChildViewById(rootView, id);
      if (male == null) {
        break missingId;
      }

      id = R.id.noAsk;
      Button noAsk = ViewBindings.findChildViewById(rootView, id);
      if (noAsk == null) {
        break missingId;
      }

      return new FragmentCreateGenderBinding((ConstraintLayout) rootView, backBtn, createNameHead,
          female, male, noAsk);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
