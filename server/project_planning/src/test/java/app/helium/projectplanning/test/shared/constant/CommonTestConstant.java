package app.helium.projectplanning.test.shared.constant;

import java.time.Instant;
import java.util.UUID;

public class CommonTestConstant {
    public static final UUID DEFAULT_USER_ID = UUID.fromString("37584e0e-fd12-490e-ad96-6bf9c980f4e6");
    public static final UUID DEFAULT_TEST_PROJECT_ID = UUID.fromString("1d6846ce-0a75-4a39-a49e-dea0d4be8b40");
    public static final Instant FIXED_NOW_INSTANT = Instant.parse("2025-04-11T00:00:00Z");
    public static final Instant DEFAULT_TEST_START_DATE = Instant.parse("2025-04-11T00:00:00Z");
    public static final Instant DEFAULT_TEST_END_DATE = Instant.parse("2025-04-27T00:00:00Z");
    public static final UUID DEFAULT_TEST_SPRINT_ID = UUID.fromString("24f402bb-d5de-4886-b545-2a4cb0361631");
    public static final UUID DEFAULT_TEST_ISSUE_ID = UUID.fromString("c0d1dde6-49d7-432d-9f99-3744e7ce060e");
    public static final UUID DEFAULT_TEST_USER_ID = UUID.fromString("bb09b68e-d35c-4faa-a48d-490992c1f8dd");
    public static final UUID DEFAULT_TEST_ISSUE_STATUS_ID = UUID.fromString("943f5d4e-9914-4947-b26a-af049e00eb7d");
    public static final UUID DEFAULT_TEST_ISSUE_TYPE_ID = UUID.fromString("7b5e626e-9bb9-45ed-b19c-51a35390ad23");
}
