package com.apprecovery.volumeimage;

import android.util.Log;
import com.apprecovery.databasecheck.CheckFlags;
import com.apprecovery.databasecheck.CheckResult;
import com.apprecovery.exchange.*;
import com.apprecovery.sql.SqlInstance;
import com.apprecovery.sql.SqlInstanceCollection;
import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/29/13
 * Time: 8:48 PM
 */

@ContractXmlType(elementName = "volumeImageSummary")
public class VolumeImageSummary implements Serializable
{
    private static final String LogClassTag = "[VolumeImageSummary]";
    public static final int GRAY = 0;
    public static final int GREEN = 1;
    public static final int RED = 2;
    public static final int YELLOW = 3;

    private String encryptionKeyId;
    private boolean hasExchangeMetadata;
    private boolean hasSqlServerMetadata;
    private String id;
    private long size;
    private String volumeDisplayName;
    private VolumeImageComponents volumeImageComponents;

    @ContractXmlMethod(elementName = "encryptionKeyId")
    public String getEncryptionKeyId()
    {
        return encryptionKeyId;
    }

    @ContractXmlMethod(elementName = "encryptionKeyId", isSetter = true)
    public void setEncryptionKeyId(String encryptionKeyId)
    {
        this.encryptionKeyId = encryptionKeyId;
    }

    @ContractXmlMethod(elementName = "hasExchangeMetadata")
    public boolean isHasExchangeMetadata()
    {
        return hasExchangeMetadata;
    }

    @ContractXmlMethod(elementName = "hasExchangeMetadata", isSetter = true)
    public void setHasExchangeMetadata(boolean hasExchangeMetadata)
    {
        this.hasExchangeMetadata = hasExchangeMetadata;
    }

    @ContractXmlMethod(elementName = "hasSqlServerMetadata")
    public boolean isHasSqlServerMetadata()
    {
        return hasSqlServerMetadata;
    }

    @ContractXmlMethod(elementName = "hasSqlServerMetadata", isSetter = true)
    public void setHasSqlServerMetadata(boolean hasSqlServerMetadata)
    {
        this.hasSqlServerMetadata = hasSqlServerMetadata;
    }

    @ContractXmlMethod(elementName = "id")
    public String getId()
    {
        return id;
    }

    @ContractXmlMethod(elementName = "id", isSetter = true)
    public void setId(String id)
    {
        this.id = id;
    }

    @ContractXmlMethod(elementName = "size")
    public long getSize()
    {
        return size;
    }

    @ContractXmlMethod(elementName = "size", isSetter = true)
    public void setSize(long size)
    {
        this.size = size;
    }

    @ContractXmlMethod(elementName = "volumeDisplayName")
    public String getVolumeDisplayName()
    {
        return volumeDisplayName;
    }

    @ContractXmlMethod(elementName = "volumeDisplayName", isSetter = true)
    public void setVolumeDisplayName(String volumeDisplayName)
    {
        this.volumeDisplayName = volumeDisplayName;
    }

    @ContractXmlMethod(elementName = "volumeImageComponents")
    public VolumeImageComponents getVolumeImageComponents()
    {
        return volumeImageComponents;
    }

    @ContractXmlMethod(elementName = "volumeImageComponents", isSetter = true)
    public void setVolumeImageComponents(VolumeImageComponents volumeImageComponents)
    {
        this.volumeImageComponents = volumeImageComponents;
    }

    public int getVolumeImageState()
    {
        Log.d(LogClassTag, "Detect volume image state");

        if (!isHasExchangeMetadata() && !isHasSqlServerMetadata())
        {
            Log.d(LogClassTag, "The volume has not any check components. State is GRAY.");
            return GRAY;
        }

        if (isHasExchangeMetadata())
        {
            MailStoreCollection mailStoreCollection = getVolumeImageComponents().getExchangeServer().getMailStores();
            if (mailStoreCollection != null)
            {
                Log.d(LogClassTag, "Detect state for Exchange 2010/2013.");
                for (MailStore mailStore : mailStoreCollection)
                {
                    int checkResult = mailStore.getCheckResults().getCheckResults();
                    if ((checkResult & CheckFlags.MountCheckFailed) != 0)
                    {
                        Log.d(LogClassTag, "The volume image state for Exchange 2010/2013 is RED.");
                        return RED;
                    }

                    if ((checkResult & CheckFlags.MountCheckPassed) != 0 || (checkResult & CheckFlags.ChecksumCheckPassed) != 0)
                    {
                        Log.d(LogClassTag, "The volume image state for Exchange 2010/2013 is GREEN.");
                        return GREEN;
                    }
                }

                Log.d(LogClassTag, "The volume image state for Exchange 2010/2013 is YELLOW.");
                return YELLOW;
            }

            LegacyStorageGroupCollection legacyStorageGroupCollection = getVolumeImageComponents().getExchangeServer().getLegacyStorageGroups();
            if (legacyStorageGroupCollection != null)
            {
                Log.d(LogClassTag, "Detect state for Exchange 2007.");
                for (LegacyStorageGroup legacyStorageGroup : legacyStorageGroupCollection)
                {
                    LegacyMailStoreCollection legacyMailStoreCollection = legacyStorageGroup.getMailStores();
                    if (legacyMailStoreCollection != null)
                    {
                        for (LegacyMailStore legacyMailStore : legacyMailStoreCollection)
                        {
                            int checkResult = legacyMailStore.getCheckResults().getCheckResults();
                            if ((checkResult & CheckFlags.MountCheckFailed) != 0)
                            {
                                Log.d(LogClassTag, "The volume image state for Exchange 2007 is RED.");
                                return RED;
                            }

                            if ((checkResult & CheckFlags.MountCheckPassed) != 0 || (checkResult & CheckFlags.ChecksumCheckPassed) != 0)
                            {
                                Log.d(LogClassTag, "The volume image state for Exchange 2007 is GREEN.");
                                return GREEN;
                            }
                        }

                        Log.d(LogClassTag, "The volume image state for Exchange 2007 is YELLOW.");
                        return YELLOW;
                    }
                }
            }
        }

        if (isHasSqlServerMetadata())
        {
            SqlInstanceCollection sqlInstanceCollection = getVolumeImageComponents().getSqlServer().getInstances();
            if (sqlInstanceCollection != null)
            {
                return sqlInstanceCollection.getState();
            }
        }

        Log.d(LogClassTag, "The volume image state is GRAY.");
        return GRAY;
    }
}
