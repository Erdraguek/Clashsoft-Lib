package clashsoft.cslib.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CSCollections
{
	/** Hacky way to get the component type of a list **/
	public static Collection<?>	tempCollection;
	
	/**
	 * Returns the component type of a collection
	 * 
	 * @see CSCollections#tempCollection
	 * @param collection
	 *            the collection
	 * @return the component type class
	 */
	public static Class getComponentType(Collection collection)
	{
		tempCollection = collection;
		try
		{
			Field tempCollectionField = CSCollections.class.getDeclaredField("tempCollection");
			ParameterizedType tempCollectionType = (ParameterizedType) tempCollectionField.getGenericType();
			Class tempCollectionClass = (Class) tempCollectionType.getActualTypeArguments()[0];
			return tempCollectionClass;
		}
		catch (NoSuchFieldException ex)
		{
			ex.printStackTrace();
		}
		catch (SecurityException ex)
		{
			ex.printStackTrace();
		}
		return Object.class;
	}
	
	/**
	 * Splits a list into sub-lists of size {@code maxLength}
	 * 
	 * @param list
	 *            the list
	 * @param maxLength
	 *            the maximum length
	 * @return the list of lists
	 */
	public static <T> List<List<T>> split(List<T> list, int maxLength)
	{
		Class clazz = list.getClass().getComponentType();
		int arrays = (int) Math.ceil((float) list.size() / (float) maxLength);
		List<List<T>> ret = new ArrayList();
		
		for (int i = 0; i < ret.size(); i++)
		{
			List<T> reti = new ArrayList();
			for (int j = 0; j < maxLength && (j + (i * maxLength)) < list.size(); j++)
				reti.add(list.get(j + (i * maxLength)));
			ret.add(reti);
		}
		return ret;
	}
	
	/**
	 * Concats two lists
	 * 
	 * @param list1
	 *            the first list
	 * @param list2
	 *            the second list
	 * @return the list
	 */
	public static <T> List<T> concat(List<T> list1, List<T> list2)
	{
		List<T> ret = new ArrayList<T>(list1.size() + list2.size());
		ret.addAll(list1);
		ret.addAll(list2);
		return ret;
	}
	
	/**
	 * Removes all duplicates from a collection
	 * 
	 * @param collection
	 *            the collection
	 * @return the collection without duplicates
	 */
	public static <T> Collection<T> removeDuplicates(Collection<T> collection)
	{
		if (collection != null && collection.size() > 0)
		{
			Collection<T> result = new ArrayList<T>();
			for (T t1 : collection)
			{
				boolean duplicate = false;
				for (T t2 : result)
				{
					if (Objects.equals(t1, t2))
						duplicate = true;
					break;
				}
				if (!duplicate)
					result.add(t1);
			}
			return result;
		}
		return collection;
	}
	
	/**
	 * Creates a new Array List from the array
	 * 
	 * @see CSArrays#asList(Object...)
	 * @param array
	 *            the array
	 * @return the array list
	 */
	public static <T> List<T> fromArray(T... array)
	{
		return CSArrays.asList(array);
	}
	
	/**
	 * Creates an array from the given {@code collection}
	 * 
	 * @see CSCollections#toArray(Class, Collection)
	 * @param collection
	 *            the collection
	 * @return the array
	 */
	public static <T> T[] toArray(Collection<T> collection)
	{
		return toArray(getComponentType(collection), collection);
	}
	
	/**
	 * Creates an array from the given {@code collection}
	 * 
	 * @see CSCollections#toArray(Class, Collection)
	 * @param type
	 *            the expected type
	 * @param collection
	 *            the collection
	 * @return the array
	 */
	public static <T> T[] toArray(Class type, Collection<T> collection)
	{
		T[] array = (T[]) Array.newInstance(type, collection.size());
		collection.toArray(array);
		return array;
	}
	
	/**
	 * Returns the first index of the {@code object} in the {@code list}
	 * 
	 * @param list
	 *            the list
	 * @param object
	 *            the object
	 * @return the first index of the object in the list
	 */
	public static <T> int indexOf(List<T> list, T object)
	{
		return indexOf(list, 0, object);
	}
	
	/**
	 * Returns the first index after {@code start} of the {@code object} in the
	 * {@code list}
	 * 
	 * @param list
	 *            the list
	 * @param start
	 *            the index to start from
	 * @param object
	 *            the object
	 * @return the first index of the object in the array
	 */
	public static <T> int indexOf(List<T> list, int start, T object)
	{
		for (int i = start; i < list.size(); i++)
		{
			if (Objects.equals(object, list.get(i)))
				return i;
		}
		return -1;
	}
	
	/**
	 * Returns the last index of the {@code object} in the {@code list}
	 * 
	 * @param list
	 *            the list
	 * @param object
	 *            the object
	 * @return the last index of the object in the array
	 */
	public static <T> int lastIndexOf(List<T> list, T object)
	{
		return lastIndexOf(list, list.size() - 1, object);
	}
	
	/**
	 * Returns the last index before {@code start} of the {@code object} in the
	 * {@code list}
	 * 
	 * @param list
	 *            the list
	 * @param start
	 *            the index to start from
	 * @param object
	 *            the object
	 * @return the last index of the object in the array
	 */
	public static <T> int lastIndexOf(List<T> list, int start, T object)
	{
		for (int i = start; i >= 0; i--)
		{
			if (Objects.equals(object, list.get(i)))
				return i;
		}
		return -1;
	}
	
	/**
	 * Returns the first index of the any of the {@code objects} in the
	 * {@code list}
	 * 
	 * @param list
	 *            the list
	 * @param objects
	 *            the objects
	 * @return the first index of the object in the list
	 */
	public static <T> int indexOfAny(List<T> list, T... objects)
	{
		for (T object : objects)
		{
			int index = indexOf(list, object);
			if (index != -1)
				return index;
		}
		return -1;
	}
	
	/**
	 * Returns the last index of any of the {@code objects} in the {@code list}
	 * 
	 * @param list
	 *            the list
	 * @param objects
	 *            the objects
	 * @return the last index of the object in the list
	 */
	public static <T> int lastIndexOfAny(List<T> list, T... objects)
	{
		for (T object : objects)
		{
			int index = lastIndexOf(list, object);
			if (index != -1)
				return index;
		}
		return -1;
	}
	
	/**
	 * Checks if {@code collection} contains the {@code object}.
	 * 
	 * @param collection
	 *            the collection
	 * @param object
	 *            the object
	 * @return true, if the collection contains the object
	 */
	public static <T> boolean contains(Collection<T> collection, T object)
	{
		for (T t : collection)
			if (Objects.equals(t, object))
				return true;
		return false;
	}
	
	/**
	 * Checks if {@code collection} contains any of the {@code objects}.
	 * 
	 * @param collection
	 *            the collection
	 * @param objects
	 *            the objects
	 * @return true, if the collection contains any of the objects
	 */
	public static <T> boolean containsAny(Collection<T> collection, T... objects)
	{
		for (T object : objects)
			if (contains(collection, object))
				return true;
		return false;
	}
	
	/**
	 * Checks if {@code collection} contains all {@code objects}.
	 * 
	 * @param collection
	 *            the collection
	 * @param objects
	 *            the objects
	 * @return true, if the collection contains all objects
	 */
	public static <T> boolean containsAll(Collection<T> collection, T... objects)
	{
		for (T object : objects)
			if (!contains(collection, object))
				return false;
		return true;
	}
}
