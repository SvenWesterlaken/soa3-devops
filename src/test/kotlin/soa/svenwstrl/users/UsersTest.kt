package soa.svenwstrl.users

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import soa.svenwstrl.notifications.Notifiable

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsersTest {

    @Test
    fun developer() {
        val member = Developer("TestPerson")
        assertThat(member.getRole()).isEqualTo(TeamMember.Role.DEVELOPER)
    }

    @Test
    fun scrumMaster() {
        val member = ScrumMaster("TestPerson")
        assertThat(member.getRole()).isEqualTo(TeamMember.Role.SCRUM_MASTER)
    }

    @Test
    fun productOwner() {
        val member = ProductOwner("TestPerson")
        assertThat(member.getRole()).isEqualTo(TeamMember.Role.PRODUCT_OWNER)
    }

    @Test
    fun name() {
        val member = ProductOwner("TestPerson")

        assertThat(member.getName()).isEqualTo("TestPerson")

        member.setName("NewTestPerson")

        assertThat(member.getName()).isEqualTo("NewTestPerson")
    }

    @Test
    fun `Add & Remove Notification Type`() {
        val member = ProductOwner("TestPerson")

        assertThat(member.getSelectedNotificationTypes()).hasSize(0).isEmpty()

        member.addNotificationType(Notifiable.Type.EMAIL)

        assertThat(member.getSelectedNotificationTypes()).hasSize(1).isNotEmpty().contains(Notifiable.Type.EMAIL)

        member.removeNotificationType(Notifiable.Type.EMAIL)
        
        assertThat(member.getSelectedNotificationTypes()).hasSize(0).isEmpty()
    }
}