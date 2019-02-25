package soa.svenwstrl

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import soa.svenwstrl.forum.Forum
import soa.svenwstrl.main.Project
import soa.svenwstrl.management.ProductBacklog
import soa.svenwstrl.management.SprintBacklog
import soa.svenwstrl.users.Developer
import soa.svenwstrl.users.ProductOwner
import soa.svenwstrl.users.TeamMember
import java.io.ByteArrayOutputStream
import java.io.PrintStream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectTest {
    private val outContent = ByteArrayOutputStream()
    private val errContent = ByteArrayOutputStream()
    private val originalOut = System.out
    private val originalErr = System.err

    private lateinit var project: Project
    private lateinit var productOwner: ProductOwner

    @BeforeAll
    fun setUpStreams() {
        System.setOut(PrintStream(outContent))
        System.setErr(PrintStream(errContent))
    }

    @AfterAll
    fun restoreStreams() {
        System.setOut(originalOut)
        System.setErr(originalErr)
    }

    @BeforeEach
    fun refreshProject() {
        this.productOwner = mockk(relaxed = true)
        this.project = Project("TestProject", this.productOwner)
    }

    @Test
    fun main() {
        this.project.main(null)
        assertThat(outContent.toString()).isEqualTo("Start Project")
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class `Getters & Setters` {

        @Test
        fun `Name Getter & Setter`() {

            assertThat(project.getName()).isEqualTo("TestProject")

            project.setName("New TestProject")

            assertThat(project.getName()).isEqualTo("New TestProject")

        }

        @Test
        fun `Product Backlog getter`() {
            assertThat(project.getProductBacklog()).isNotNull().isInstanceOf(ProductBacklog::class.java)
        }

        @Test
        fun `Forum getter`() {
            assertThat(project.getForum()).isNotNull().isInstanceOf(Forum::class.java)
        }

    }

    @Test
    fun `Add & remove sprint backlog`() {

        val sprintBacklog = mockk<SprintBacklog>(relaxed = true)

        this.project.addSprintBacklog(sprintBacklog)

        assertThat(project.getSprintBacklogs()).hasSize(1)
        assertThat(project.getSprintBacklogs()).isNotEmpty()
        assertThat(project.getSprintBacklog(0)).isEqualTo(sprintBacklog)

        this.project.removeSprintBacklog(sprintBacklog)


        assertThat(project.getSprintBacklogs()).hasSize(0)
        assertThat(project.getSprintBacklogs()).isEmpty()
        assertThat(Assertions.catchThrowable { project.getSprintBacklog(0) }).isInstanceOf(IndexOutOfBoundsException::class.java)

    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class `Team Members & Product Owner` {

        @Test
        fun `Add & remove team members (Non Product Owner)`() {

            val teamMember = mockk<Developer>(relaxed = true)

            project.addMember(teamMember)

            assertThat(project.getMembers()).hasSize(1)
            assertThat(project.getMembers()).isNotEmpty()
            assertThat(project.getMember(0)).isEqualTo(teamMember)

            project.removeMember(teamMember)

            assertThat(project.getMembers()).hasSize(0)
            assertThat(project.getMembers()).isEmpty()
            assertThat(Assertions.catchThrowable { project.getMember(0) }).isInstanceOf(IndexOutOfBoundsException::class.java)
        }

        @Test
        fun `Add & remove team members (Product Owner)`() {

            val teamMember = mockk<ProductOwner>(relaxed = true)

            every { teamMember.getRole() } returns TeamMember.Role.PRODUCT_OWNER

            assertThat(Assertions.catchThrowable { project.addMember(teamMember) }).isInstanceOf(NotImplementedError::class.java)
            assertThat(Assertions.catchThrowable { project.removeMember(teamMember) }).isInstanceOf(NotImplementedError::class.java)

        }

        @Test
        fun `Set & get product owner`() {

            val newProductOwner = mockk<ProductOwner>(relaxed = true)

            assertThat(project.getProductOwner()).isEqualTo(productOwner)

            project.setProductOwner(newProductOwner)

            assertThat(project.getProductOwner()).isEqualTo(newProductOwner)
            assertThat(project.getProductOwner()).isNotEqualTo(productOwner)

        }
    }


}