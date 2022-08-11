package com.sparta.myblog.scheduler.ec;

import com.sparta.myblog.domain.Post;
import com.sparta.myblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Scheduler {
        private final Logger logger = LoggerFactory.getLogger(Scheduler.class);
        private final PostRepository postRepository;
// 초, 분, 시, 일, 월, 주 순서
    @Scheduled(cron = "0 0 1 * * *")
    public void updatePrice() throws InterruptedException {
            List<Post> postList = postRepository.findAll();
            for(Post p : postList) {
                if(p.getComments().size() == 0){
                    logger.info("게시물 <"+p.getTitle()+">이 삭제되었습니다.");
                    postRepository.delete(p);
                }
            }
    }
}
