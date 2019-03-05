package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class EnvController {

    private final Map<String, String> envControls;

    public EnvController(@Value("${port:NOT SET}") String port,
                         @Value("${memory.limit:NOT SET}") String memoryLimit,
                         @Value("${cf.instance.index:NOT SET}") String instanceIndex,
                        @Value("${cf.instance.addr:NOT SET}") String instanceAddr){

        envControls = new HashMap<>();

        envControls.put("PORT", port);
        envControls.put("MEMORY_LIMIT", memoryLimit);
        envControls.put("CF_INSTANCE_INDEX", instanceIndex);
        envControls.put("CF_INSTANCE_ADDR", instanceAddr);
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        return envControls;

    }
}

