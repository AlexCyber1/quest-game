package com.javarush.quest.service;

import com.javarush.quest.model.QuestStep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestServiceTest {

    private static final String EXPECTED_STEP_START_ID = "start";
    private static final String EXPECTED_STEP_BRIDGE_ID = "bridge";
    private static final String EXPECTED_STEP_CAPTAIN_ID = "captain";
    private static final String EXPECTED_STEP_WIN_ID = "win";
    private static final String EXPECTED_STEP_LOSE1_ID = "lose1";
    private static final String EXPECTED_STEP_LOSE2_ID = "lose2";
    private static final String EXPECTED_STEP_LOSE3_ID = "lose3";

    private static final String EXPECTED_START_TEXT = "Ты потерял память. Принять вызов НЛО?";
    private static final String EXPECTED_OPTION1_START = "Принять вызов";
    private static final String EXPECTED_OPTION2_START = "Отклонить вызов";
    private static final String EXPECTED_WIN_TEXT_CONTAINS = "Победа";

    private static final String NON_EXISTENT_STEP_ID = "non-existent";

    private QuestService questService;

    @BeforeEach
    void setUp() {
        questService = new QuestService();
    }

    @Test
    @DisplayName("Тест: начальный шаг не должен быть null")
    void testGetStartStep_NotNull() {
        QuestStep step = questService.getStep(EXPECTED_STEP_START_ID);
        assertNotNull(step, "Шаг 'start' не должен быть null");
    }

    @Test
    @DisplayName("Тест: ID начального шага корректный")
    void testGetStartStep_Id() {
        QuestStep step = questService.getStep(EXPECTED_STEP_START_ID);
        assertEquals(EXPECTED_STEP_START_ID, step.getId(),
                "ID шага должен быть `" + EXPECTED_STEP_START_ID + "`");
    }

    @Test
    @DisplayName("Тест: текст начального шага корректный")
    void testGetStartStep_Text() {
        QuestStep step = questService.getStep(EXPECTED_STEP_START_ID);
        assertEquals(EXPECTED_START_TEXT, step.getText(),
                "Должна быть строка: `" + EXPECTED_START_TEXT + "`");
    }

    @Test
    @DisplayName("Тест: первый вариант ответа начального шага")
    void testGetStartStep_Option1() {
        QuestStep step = questService.getStep(EXPECTED_STEP_START_ID);
        assertEquals(EXPECTED_OPTION1_START, step.getOption1(),
                "Должен быть: `" + EXPECTED_OPTION1_START + "`");
    }

    @Test
    @DisplayName("Тест: второй вариант ответа начального шага")
    void testGetStartStep_Option2() {
        QuestStep step = questService.getStep(EXPECTED_STEP_START_ID);
        assertEquals(EXPECTED_OPTION2_START, step.getOption2(),
                "Должен быть: `" + EXPECTED_OPTION2_START + "`");
    }

    @Test
    @DisplayName("Тест: следующий шаг по первому варианту")
    void testGetStartStep_NextStepId1() {
        QuestStep step = questService.getStep(EXPECTED_STEP_START_ID);
        assertEquals(EXPECTED_STEP_BRIDGE_ID, step.getNextStepId1(),
                "Должен быть: `" + EXPECTED_STEP_BRIDGE_ID + "`");
    }

    @Test
    @DisplayName("Тест: следующий шаг по второму варианту")
    void testGetStartStep_NextStepId2() {
        QuestStep step = questService.getStep(EXPECTED_STEP_START_ID);
        assertEquals(EXPECTED_STEP_LOSE1_ID, step.getNextStepId2(),
                "Должен быть: `" + EXPECTED_STEP_LOSE1_ID + "`");
    }

    @Test
    @DisplayName("Тест: шаг победы не должен быть null")
    void testGetWinStep_NotNull() {
        QuestStep step = questService.getStep(EXPECTED_STEP_WIN_ID);
        assertNotNull(step, "Шаг 'win' не должен быть null");
    }

    @Test
    @DisplayName("Тест: текст шага победы содержит слово 'Победа'")
    void testGetWinStep_TextContainsWin() {
        QuestStep step = questService.getStep(EXPECTED_STEP_WIN_ID);
        assertTrue(step.getText().contains(EXPECTED_WIN_TEXT_CONTAINS),
                "Текст должен содержать слово `" + EXPECTED_WIN_TEXT_CONTAINS + "`");
    }

    @Test
    @DisplayName("Тест: у шага победы нет первого варианта ответа")
    void testGetWinStep_NoOption1() {
        QuestStep step = questService.getStep(EXPECTED_STEP_WIN_ID);
        assertNull(step.getOption1(), "Не должно быть вариантов ответа");
    }

    @Test
    @DisplayName("Тест: у шага победы нет второго варианта ответа")
    void testGetWinStep_NoOption2() {
        QuestStep step = questService.getStep(EXPECTED_STEP_WIN_ID);
        assertNull(step.getOption2(), "Не должно быть вариантов ответа");
    }

    @Test
    @DisplayName("Тест: несуществующий шаг возвращает null")
    void testGetNonExistentStep() {
        QuestStep step = questService.getStep(NON_EXISTENT_STEP_ID);
        assertNull(step, "Несуществующий шаг должен возвращать null");
    }

    @Test
    @DisplayName("Тест: win - финальный шаг")
    void testIsFinalStep_Win() {
        assertTrue(questService.isFinalStep(EXPECTED_STEP_WIN_ID),
                "`win` должен быть финальным шагом");
    }

    @Test
    @DisplayName("Тест: lose1 - финальный шаг")
    void testIsFinalStep_Lose1() {
        assertTrue(questService.isFinalStep(EXPECTED_STEP_LOSE1_ID),
                "`lose1` должен быть финальным шагом");
    }

    @Test
    @DisplayName("Тест: lose2 - финальный шаг")
    void testIsFinalStep_Lose2() {
        assertTrue(questService.isFinalStep(EXPECTED_STEP_LOSE2_ID),
                "`lose2` должен быть финальным шагом");
    }

    @Test
    @DisplayName("Тест: lose3 - финальный шаг")
    void testIsFinalStep_Lose3() {
        assertTrue(questService.isFinalStep(EXPECTED_STEP_LOSE3_ID),
                "`lose3` должен быть финальным шагом");
    }

    @Test
    @DisplayName("Тест: start - не финальный шаг")
    void testIsFinalStep_Start() {
        assertFalse(questService.isFinalStep(EXPECTED_STEP_START_ID),
                "`start` не должен быть финальным шагом");
    }

    @Test
    @DisplayName("Тест: bridge - не финальный шаг")
    void testIsFinalStep_Bridge() {
        assertFalse(questService.isFinalStep(EXPECTED_STEP_BRIDGE_ID),
                "`bridge` не должен быть финальным шагом");
    }

    @Test
    @DisplayName("Тест: captain - не финальный шаг")
    void testIsFinalStep_Captain() {
        assertFalse(questService.isFinalStep(EXPECTED_STEP_CAPTAIN_ID),
                "`captain` не должен быть финальным шагом");
    }

    @Test
    @DisplayName("Тест: связь start -> bridge")
    void testQuestBranching_StartToBridge() {
        QuestStep start = questService.getStep(EXPECTED_STEP_START_ID);
        assertEquals(EXPECTED_STEP_BRIDGE_ID, start.getNextStepId1());
    }

    @Test
    @DisplayName("Тест: связь start -> lose1")
    void testQuestBranching_StartToLose1() {
        QuestStep start = questService.getStep(EXPECTED_STEP_START_ID);
        assertEquals(EXPECTED_STEP_LOSE1_ID, start.getNextStepId2());
    }

    @Test
    @DisplayName("Тест: связь bridge -> captain")
    void testQuestBranching_BridgeToCaptain() {
        QuestStep bridge = questService.getStep(EXPECTED_STEP_BRIDGE_ID);
        assertEquals(EXPECTED_STEP_CAPTAIN_ID, bridge.getNextStepId1());
    }

    @Test
    @DisplayName("Тест: связь bridge -> lose2")
    void testQuestBranching_BridgeToLose2() {
        QuestStep bridge = questService.getStep(EXPECTED_STEP_BRIDGE_ID);
        assertEquals(EXPECTED_STEP_LOSE2_ID, bridge.getNextStepId2());
    }

    @Test
    @DisplayName("Тест: связь captain -> win")
    void testQuestBranching_CaptainToWin() {
        QuestStep captain = questService.getStep(EXPECTED_STEP_CAPTAIN_ID);
        assertEquals(EXPECTED_STEP_WIN_ID, captain.getNextStepId1());
    }

    @Test
    @DisplayName("Тест: связь captain -> lose3")
    void testQuestBranching_CaptainToLose3() {
        QuestStep captain = questService.getStep(EXPECTED_STEP_CAPTAIN_ID);
        assertEquals(EXPECTED_STEP_LOSE3_ID, captain.getNextStepId2());
    }
}