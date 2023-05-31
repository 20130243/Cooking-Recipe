package com.example.cookingrecipe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingrecipe.Adapter.MessageAdapter;
import com.example.cookingrecipe.Domain.Model.Message;
import com.example.cookingrecipe.databinding.FragmentChatBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatFragment extends Fragment {
    RecyclerView recyclerView;
    TextView welcomeTextView;
    EditText messageEditText;
    ImageButton sendButton;
    List<Message> messageList;
    MessageAdapter messageAdapter;
    Button login_btn;
    ConstraintLayout login_layout;
    ConstraintLayout chat_layout;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS) // Tăng thời gian chờ kết nối
            .readTimeout(60, TimeUnit.SECONDS) // Tăng thời gian chờ đọc dữ liệu
            .writeTimeout(60, TimeUnit.SECONDS) // Tăng thời gian chờ ghi dữ liệu
            .build();
    FragmentChatBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        recyclerView = binding.recyclerView;
        messageList = new ArrayList<>();

        welcomeTextView = binding.introText;
        messageEditText = binding.editTextText;
        sendButton = binding.searchButton;
        login_layout = binding.login;
        chat_layout = binding.chatLayout;
        login_btn = binding.loginBtn;
        login_btn.setOnClickListener(v -> startActivity(new Intent(this.getActivity(), Login.class)));

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());


        if (acct != null) {
            chat_layout.setVisibility(View.VISIBLE);
            login_layout.setVisibility(View.GONE);
            messageAdapter = new MessageAdapter(messageList);
            recyclerView.setAdapter(messageAdapter);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setStackFromEnd(true);
            recyclerView.setLayoutManager(llm);

            sendButton.setOnClickListener((v) -> {
                String question = messageEditText.getText().toString().trim();
                addToChat(question, Message.SENT_BY_ME);
                messageEditText.setText("");

                String find = "Cho tôi biết công thức nấu ăn của món " + question + ". Nếu " + question +
                        " không phải tên của một món ăn có công thức chế biến thì hãy trả lời là 'đây không phải là một món ăn có công thức chế biến'";
                callAPI("Cho tôi biết công thức nấu ăn của món " + question);

                welcomeTextView.setVisibility(View.GONE);
            });
        } else {
            chat_layout.setVisibility(View.GONE);
            login_layout.setVisibility(View.VISIBLE);
        }
        return view;
    }


    void addToChat(String message, String sentBy) {
        getActivity().runOnUiThread((Runnable) () -> {
            messageList.add(new Message(message, sentBy));
            messageAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
        });
    }

    void addResponse(String response) {
        messageList.remove(messageList.size() - 1);
        addToChat(response, Message.SENT_BY_BOT);
    }

    void callAPI(String question) {
        messageList.add(new Message("Đợi chút xíu... ", Message.SENT_BY_BOT));

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", "text-davinci-003");
            jsonBody.put("prompt", question);
            jsonBody.put("max_tokens", 4000);
            jsonBody.put("temperature", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .header("Authorization", "Bearer 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")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("Failed to load response due to " + e.getMessage());
                addResponse("Có lõi xảy ra vui lòng thử lại sau");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        addResponse(result.trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("Failed to load response due to " + response.body().toString());
                    addResponse("Có lõi xảy ra vui lòng thử lại sau");
                }
            }
        });
    }


}