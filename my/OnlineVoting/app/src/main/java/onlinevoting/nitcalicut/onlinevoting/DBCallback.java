package onlinevoting.nitcalicut.onlinevoting;

import java.util.List;

import onlinevoting.nitcalicut.onlinevoting.model.Post;

public interface DBCallback {
    void onSuccess(List<Post> posts);
    void onSuccess(int count);
    void onSuccess(Post post);
    void onError(String msg);
}
