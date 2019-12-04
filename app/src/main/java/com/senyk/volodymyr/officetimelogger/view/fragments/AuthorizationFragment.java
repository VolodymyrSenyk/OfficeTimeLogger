package com.senyk.volodymyr.officetimelogger.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.senyk.volodymyr.officetimelogger.R;
import com.senyk.volodymyr.officetimelogger.models.dto.CredentialsDto;
import com.senyk.volodymyr.officetimelogger.models.dto.EmployeeDto;
import com.senyk.volodymyr.officetimelogger.repository.FakeRepository;
import com.senyk.volodymyr.officetimelogger.viewmodel.AuthorizationViewModel;

public class AuthorizationFragment extends Fragment {
    private AuthorizationViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_authorization, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = new AuthorizationViewModel(FakeRepository.getFakeRepository());

        view.findViewById(R.id.log_in_button).setOnClickListener(view1 -> {
            String firstName = ((TextView) view.findViewById(R.id.first_name_input_view)).getText().toString();
            String lastName = ((TextView) view.findViewById(R.id.last_name_input_view)).getText().toString();
            String middleName = ((TextView) view.findViewById(R.id.middle_name_input_view)).getText().toString();
            String password = ((TextView) view.findViewById(R.id.password_input_view)).getText().toString();
            if (firstName.equals("") || lastName.equals("") || middleName.equals("") || password.equals("")) {
                Toast.makeText(requireContext(), getString(R.string.empty_fields_error), Toast.LENGTH_LONG).show();
            } else {
                viewModel.logIn(new CredentialsDto(new EmployeeDto(firstName, lastName, middleName), password));
            }
        });

        this.viewModel.getMessage().observe(this.getViewLifecycleOwner(), message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show());
        this.viewModel.getUserId().observe(this.getViewLifecycleOwner(), userId -> {
            if (userId != -1) NavHostFragment.findNavController(this)
                    .navigate(AuthorizationFragmentDirections.actionAuthorizationFragmentToTimeLoggerFragment());
        });
    }
}
