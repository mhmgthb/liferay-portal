/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.service.persistence;

import com.liferay.portal.model.Subscription;

/**
 * The persistence interface for the subscription service.
 *
 * <p>
 * Never modify this interface directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
 * </p>
 *
 * <p>
 * Never reference this class directly, use {@link SubscriptionUtil} instead.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SubscriptionPersistenceImpl
 * @see SubscriptionUtil
 * @generated
 */
public interface SubscriptionPersistence extends BasePersistence<Subscription> {
	/**
	* Caches the subscription in the entity cache if it is enabled.
	*
	* @param subscription the subscription to cache
	*/
	public void cacheResult(com.liferay.portal.model.Subscription subscription);

	/**
	* Caches the subscriptions in the entity cache if it is enabled.
	*
	* @param subscriptions the subscriptions to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.model.Subscription> subscriptions);

	/**
	* Creates a new subscription with the primary key. Does not add the subscription to the database.
	*
	* @param subscriptionId the primary key for the new subscription
	* @return the new subscription
	*/
	public com.liferay.portal.model.Subscription create(long subscriptionId);

	/**
	* Removes the subscription with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param subscriptionId the primary key of the subscription to remove
	* @return the subscription that was removed
	* @throws com.liferay.portal.NoSuchSubscriptionException if a subscription with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription remove(long subscriptionId)
		throws com.liferay.portal.NoSuchSubscriptionException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.Subscription updateImpl(
		com.liferay.portal.model.Subscription subscription, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the subscription with the primary key or throws a {@link com.liferay.portal.NoSuchSubscriptionException} if it could not be found.
	*
	* @param subscriptionId the primary key of the subscription to find
	* @return the subscription
	* @throws com.liferay.portal.NoSuchSubscriptionException if a subscription with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription findByPrimaryKey(
		long subscriptionId)
		throws com.liferay.portal.NoSuchSubscriptionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the subscription with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param subscriptionId the primary key of the subscription to find
	* @return the subscription, or <code>null</code> if a subscription with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription fetchByPrimaryKey(
		long subscriptionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the subscriptions where userId = &#63;.
	*
	* @param userId the user id to search with
	* @return the matching subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Subscription> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the subscriptions where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param start the lower bound of the range of subscriptions to return
	* @param end the upper bound of the range of subscriptions to return (not inclusive)
	* @return the range of matching subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Subscription> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the subscriptions where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param start the lower bound of the range of subscriptions to return
	* @param end the upper bound of the range of subscriptions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Subscription> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first subscription in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching subscription
	* @throws com.liferay.portal.NoSuchSubscriptionException if a matching subscription could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchSubscriptionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last subscription in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching subscription
	* @throws com.liferay.portal.NoSuchSubscriptionException if a matching subscription could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchSubscriptionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the subscriptions before and after the current subscription in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param subscriptionId the primary key of the current subscription
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next subscription
	* @throws com.liferay.portal.NoSuchSubscriptionException if a subscription with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription[] findByUserId_PrevAndNext(
		long subscriptionId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchSubscriptionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the subscriptions where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user id to search with
	* @param classNameId the class name id to search with
	* @return the matching subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Subscription> findByU_C(
		long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the subscriptions where userId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param classNameId the class name id to search with
	* @param start the lower bound of the range of subscriptions to return
	* @param end the upper bound of the range of subscriptions to return (not inclusive)
	* @return the range of matching subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Subscription> findByU_C(
		long userId, long classNameId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the subscriptions where userId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param classNameId the class name id to search with
	* @param start the lower bound of the range of subscriptions to return
	* @param end the upper bound of the range of subscriptions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Subscription> findByU_C(
		long userId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first subscription in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param classNameId the class name id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching subscription
	* @throws com.liferay.portal.NoSuchSubscriptionException if a matching subscription could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription findByU_C_First(long userId,
		long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchSubscriptionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last subscription in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param classNameId the class name id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching subscription
	* @throws com.liferay.portal.NoSuchSubscriptionException if a matching subscription could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription findByU_C_Last(long userId,
		long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchSubscriptionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the subscriptions before and after the current subscription in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param subscriptionId the primary key of the current subscription
	* @param userId the user id to search with
	* @param classNameId the class name id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next subscription
	* @throws com.liferay.portal.NoSuchSubscriptionException if a subscription with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription[] findByU_C_PrevAndNext(
		long subscriptionId, long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchSubscriptionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the subscriptions where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param companyId the company id to search with
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @return the matching subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Subscription> findByC_C_C(
		long companyId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the subscriptions where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @param start the lower bound of the range of subscriptions to return
	* @param end the upper bound of the range of subscriptions to return (not inclusive)
	* @return the range of matching subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Subscription> findByC_C_C(
		long companyId, long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the subscriptions where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @param start the lower bound of the range of subscriptions to return
	* @param end the upper bound of the range of subscriptions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Subscription> findByC_C_C(
		long companyId, long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first subscription in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching subscription
	* @throws com.liferay.portal.NoSuchSubscriptionException if a matching subscription could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription findByC_C_C_First(
		long companyId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchSubscriptionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last subscription in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching subscription
	* @throws com.liferay.portal.NoSuchSubscriptionException if a matching subscription could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription findByC_C_C_Last(
		long companyId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchSubscriptionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the subscriptions before and after the current subscription in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param subscriptionId the primary key of the current subscription
	* @param companyId the company id to search with
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next subscription
	* @throws com.liferay.portal.NoSuchSubscriptionException if a subscription with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription[] findByC_C_C_PrevAndNext(
		long subscriptionId, long companyId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchSubscriptionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the subscription where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.portal.NoSuchSubscriptionException} if it could not be found.
	*
	* @param companyId the company id to search with
	* @param userId the user id to search with
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @return the matching subscription
	* @throws com.liferay.portal.NoSuchSubscriptionException if a matching subscription could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription findByC_U_C_C(long companyId,
		long userId, long classNameId, long classPK)
		throws com.liferay.portal.NoSuchSubscriptionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the subscription where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company id to search with
	* @param userId the user id to search with
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @return the matching subscription, or <code>null</code> if a matching subscription could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription fetchByC_U_C_C(
		long companyId, long userId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the subscription where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company id to search with
	* @param userId the user id to search with
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @return the matching subscription, or <code>null</code> if a matching subscription could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Subscription fetchByC_U_C_C(
		long companyId, long userId, long classNameId, long classPK,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the subscriptions.
	*
	* @return the subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Subscription> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the subscriptions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of subscriptions to return
	* @param end the upper bound of the range of subscriptions to return (not inclusive)
	* @return the range of subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Subscription> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the subscriptions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of subscriptions to return
	* @param end the upper bound of the range of subscriptions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Subscription> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the subscriptions where userId = &#63; from the database.
	*
	* @param userId the user id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the subscriptions where userId = &#63; and classNameId = &#63; from the database.
	*
	* @param userId the user id to search with
	* @param classNameId the class name id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_C(long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the subscriptions where companyId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param companyId the company id to search with
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C_C(long companyId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the subscription where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param companyId the company id to search with
	* @param userId the user id to search with
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_U_C_C(long companyId, long userId, long classNameId,
		long classPK)
		throws com.liferay.portal.NoSuchSubscriptionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the subscriptions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the subscriptions where userId = &#63;.
	*
	* @param userId the user id to search with
	* @return the number of matching subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the subscriptions where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user id to search with
	* @param classNameId the class name id to search with
	* @return the number of matching subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_C(long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the subscriptions where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param companyId the company id to search with
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @return the number of matching subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_C(long companyId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the subscriptions where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param companyId the company id to search with
	* @param userId the user id to search with
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @return the number of matching subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_U_C_C(long companyId, long userId, long classNameId,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the subscriptions.
	*
	* @return the number of subscriptions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}