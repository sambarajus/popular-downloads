package com.ssk.challenge.jgrog;

import java.util.Date;

public class Artifact {

    String name;
    String repo;
    String path;
    String type;
    long size;
    Date created;
    String created_by;
    Date modified;
    String modified_by;
    Date updated;

    String url;
    int downloadCount;
    Date lastDownloaded;
    String lastDownloadedBy;
    int remoteDownloadCount;
    Date remoteLastDownloaded;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Date getLastDownloaded() {
        return lastDownloaded;
    }

    public void setLastDownloaded(Date lastDownloaded) {
        this.lastDownloaded = lastDownloaded;
    }

    public String getLastDownloadedBy() {
        return lastDownloadedBy;
    }

    public void setLastDownloadedBy(String lastDownloadedBy) {
        this.lastDownloadedBy = lastDownloadedBy;
    }

    public int getRemoteDownloadCount() {
        return remoteDownloadCount;
    }

    public void setRemoteDownloadCount(int remoteDownloadCount) {
        this.remoteDownloadCount = remoteDownloadCount;
    }

    public Date getRemoteLastDownloaded() {
        return remoteLastDownloaded;
    }

    public void setRemoteLastDownloaded(Date remoteLastDownloaded) {
        this.remoteLastDownloaded = remoteLastDownloaded;
    }

    @Override
    public String toString() {
        return "Artifact{" +
                "name='" + name + '\'' +
                ", repo='" + repo + '\'' +
                ", path='" + path + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", created=" + created +
                ", createdBy='" + created_by + '\'' +
                ", modified=" + modified +
                ", modifiedBy='" + modified_by + '\'' +
                ", updated=" + updated +
                ", url='" + url + '\'' +
                ", downloadCount=" + downloadCount +
                ", lastDownloaded=" + lastDownloaded +
                ", lastDownloadedBy='" + lastDownloadedBy + '\'' +
                ", remoteDownloadCount=" + remoteDownloadCount +
                ", remoteLastDownloaded=" + remoteLastDownloaded +
                '}';
    }
}
