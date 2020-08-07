package android.assignment.telstra

import android.assignment.telstra.data.database.entities.CityInfoProvider
import org.junit.Assert.*
import org.junit.Test

class CityInfoProviderTest
{
    @Test
    fun testCityInfoProviderResult()
    {
        val testCityInfoProviderResult = CityInfoProvider(
            "Beavers",
            "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",
            "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg")

        assertEquals("Beavers",testCityInfoProviderResult.title)
        assertEquals("Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",testCityInfoProviderResult.description)
        assertEquals("http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg",testCityInfoProviderResult.imageHref)

        assertEquals("Beavers",testCityInfoProviderResult.component1())
        assertEquals("Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",testCityInfoProviderResult.component2())
        assertEquals("http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg",testCityInfoProviderResult.component3())


        println(testCityInfoProviderResult.toString())
        println(testCityInfoProviderResult.hashCode())

        val testCityInfoProviderResult1 = CityInfoProvider(
            "Beavers",
            "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",
            "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg")

        assertTrue(testCityInfoProviderResult == testCityInfoProviderResult1)

        val testCityInfoProviderResult2 = testCityInfoProviderResult.copy(title = "Dummy")
        assertEquals("Dummy",testCityInfoProviderResult2.title)

        assertFalse(testCityInfoProviderResult == testCityInfoProviderResult2)

        testCityInfoProviderResult.title = ""
        testCityInfoProviderResult.description = ""
        testCityInfoProviderResult.imageHref = ""

        assertEquals("",testCityInfoProviderResult.title)
        assertEquals("",testCityInfoProviderResult.description)
        assertEquals("",testCityInfoProviderResult.imageHref)
    }
}