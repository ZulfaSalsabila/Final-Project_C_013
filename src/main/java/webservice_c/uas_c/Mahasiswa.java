/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_c.uas_c;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ROG
 */
@Entity
@Table(name = "mahasiswa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mahasiswa.findAll", query = "SELECT m FROM Mahasiswa m"),
    @NamedQuery(name = "Mahasiswa.findById", query = "SELECT m FROM Mahasiswa m WHERE m.mahasiswaPK.id = :id"),
    @NamedQuery(name = "Mahasiswa.findByNama", query = "SELECT m FROM Mahasiswa m WHERE m.nama = :nama"),
    @NamedQuery(name = "Mahasiswa.findByNik", query = "SELECT m FROM Mahasiswa m WHERE m.mahasiswaPK.nik = :nik"),
    @NamedQuery(name = "Mahasiswa.findByAlamat", query = "SELECT m FROM Mahasiswa m WHERE m.alamat = :alamat")})
public class Mahasiswa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MahasiswaPK mahasiswaPK;
    @Column(name = "nama")
    private String nama;
    @Column(name = "alamat")
    private String alamat;
    @Lob
    @Column(name = "picture")
    private byte[] picture;

    public Mahasiswa() {
    }

    public Mahasiswa(MahasiswaPK mahasiswaPK) {
        this.mahasiswaPK = mahasiswaPK;
    }

    public Mahasiswa(int id, int nik) {
        this.mahasiswaPK = new MahasiswaPK(id, nik);
    }

    public MahasiswaPK getMahasiswaPK() {
        return mahasiswaPK;
    }

    public void setMahasiswaPK(MahasiswaPK mahasiswaPK) {
        this.mahasiswaPK = mahasiswaPK;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mahasiswaPK != null ? mahasiswaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mahasiswa)) {
            return false;
        }
        Mahasiswa other = (Mahasiswa) object;
        if ((this.mahasiswaPK == null && other.mahasiswaPK != null) || (this.mahasiswaPK != null && !this.mahasiswaPK.equals(other.mahasiswaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "webservice_c.uas_c.Mahasiswa[ mahasiswaPK=" + mahasiswaPK + " ]";
    }
    
}
